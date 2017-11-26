package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.LevelDTO;
import hu.unideb.inf.coders.dto.UserAttackDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.enums.SkillTypes;
import hu.unideb.inf.coders.util.SkillUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;

public class UserAttackManagerImpl implements UserAttackManager {

    private final int ATTACK_ENERGY_REQUIREMENT = 10;
    private final int ATTACK_TIME_IN_MINUTES = 60;

    @Autowired
    private SkillUtil skillUtil;

    @Autowired
    private JobManager jobManager;

    @Autowired
    private EnergyService energyService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAttackService userAttackService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private MoneyService moneyService;

    @Autowired
    private XPService xpService;

    @Autowired
    private SkillService skillService;

    @Override
    public boolean canStartAttack(UserDTO attackerUserDTO, UserDTO defenderUserDTO) {

        if (isThereAlreadyActiveJob(attackerUserDTO)) return false;

        if (isThereAlreadyActiveAttack(attackerUserDTO)) return false;

        if (!isEnergyEnough(attackerUserDTO)) return false;

        if (!areSkillRequirementsMet(attackerUserDTO)) return false;

        if(!areLevelDifferencesProper(attackerUserDTO, defenderUserDTO)) return false;

        return true;

    }

    @Override
    public UserAttackDTO startAttack(UserDTO attackerUserDTO, UserDTO defenderUserDTO) {

        energyService.decreaseEnergy(attackerUserDTO, ATTACK_ENERGY_REQUIREMENT);

        UserAttackDTO userAttackDTO = new UserAttackDTO();
        userAttackDTO.setAttackerId(attackerUserDTO.getId());
        userAttackDTO.setDefenderId(defenderUserDTO.getId());
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = LocalDateTime.now().plusMinutes(ATTACK_TIME_IN_MINUTES);
        userAttackDTO.setStart(start);
        userAttackDTO.setFinish(finish);
        userAttackDTO.setGainedRewards(false);

        userService.save(attackerUserDTO);
        userAttackService.save(userAttackDTO);

        return userAttackDTO;

    }

    @Override
    public boolean canFinishAttack(UserAttackDTO userAttackDTO) {

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(userAttackDTO.getFinish())) return false;

        if (userAttackDTO.isGainedRewards()) return false;

        return true;
    }

    @Override
    public void finishAttack(UserAttackDTO userAttackDTO) {

        UserDTO attackerUserDTO = userService.getUserById(userAttackDTO.getAttackerId());
        UserDTO defenderUserDTO = userService.getUserById(userAttackDTO.getDefenderId());

        UserDTO winnerUserDTO = getAttackWinner(attackerUserDTO, defenderUserDTO);
        LevelDTO levelDTO = levelService.findByLevel(winnerUserDTO.getLevel());

        xpService.gain(winnerUserDTO, calculateAttackWinnerXpGain(winnerUserDTO.getLevel()), levelDTO);

        moneyService.increaseMoney(winnerUserDTO, calculateAttackWinnerMoneyGain(winnerUserDTO.getLevel()));

        userAttackDTO.setGainedRewards(true);

        userService.save(winnerUserDTO);
        userAttackService.save(userAttackDTO);

    }

    @Override
    public UserAttackDTO getActiveAttack(UserDTO attackerUserDTO) {

        return userAttackService.getActiveAttack(attackerUserDTO);

    }

    public boolean isThereAlreadyActiveJob(UserDTO userDTO) {

        return jobManager.getActiveJob(userDTO) != null;

    }

    public boolean isThereAlreadyActiveAttack(UserDTO attackerUserDTO) {

        return getActiveAttack(attackerUserDTO) != null;

    }

    public boolean isEnergyEnough(UserDTO attackerUserDTO) {

        return attackerUserDTO.getEnergy() >= ATTACK_ENERGY_REQUIREMENT;

    }

    public boolean areSkillRequirementsMet(UserDTO attackerUserDTO) {

        //TODO discuss requirements
        return skillUtil.areSkillRequirementsMet(attackerUserDTO.getSkills(), "0");

    }

    public boolean areLevelDifferencesProper(UserDTO attackerUserDTO, UserDTO defenderUserDTO) {

        return Math.abs(attackerUserDTO.getLevel() - defenderUserDTO.getLevel()) <= 5;

    }

    public UserDTO getAttackWinner(UserDTO attackerUserDTO, UserDTO defenderUserDTO) {

        String attackerSkills = attackerUserDTO.getSkills();

        Set<Long> attackerSkillIds = skillUtil.extractSkillIds(attackerSkills);

        int attackSkillCounter = 0;

        for(Long attackerSkillId : attackerSkillIds) {

            SkillTypes skillType = skillService.findById(attackerSkillId).getType();

            if (skillType == SkillTypes.OFFENSIVE || skillType == SkillTypes.ADAPTIVE) {
                attackSkillCounter++;
            }

        }

        String defenderSkills = attackerUserDTO.getSkills();

        Set<Long> defenderSkillIds = skillUtil.extractSkillIds(defenderSkills);

        int defendSkillCounter = 0;

        for(Long defenderSkillId : defenderSkillIds) {

            SkillTypes skillType = skillService.findById(defenderSkillId).getType();

            if (skillType == SkillTypes.DEFENSIVE || skillType == SkillTypes.ADAPTIVE) {
                defendSkillCounter++;
            }

        }

        Random r = new Random();
        double attackerLuck = 1.0 + (1.4 - 1.0) * r.nextDouble();
        double defenderLuck = 1.0 + (1.4 - 1.0) * r.nextDouble();

        if (attackSkillCounter * attackerLuck >= defendSkillCounter * defenderLuck) {
            return attackerUserDTO;
        } else {
            return defenderUserDTO;
        }

    }

    public int calculateAttackWinnerXpGain(int level) {

        //TODO need to discuss it
        return 100 * level;

    }

    public int calculateAttackWinnerMoneyGain(int level) {

        //TODO need to discuss it
        return 50 * level;

    }

}
