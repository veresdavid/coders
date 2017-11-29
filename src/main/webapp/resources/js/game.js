var secondsLeft = 0;
var interval;
var energyTimeout;

$(document).ready(init);

function init(){
	$.when(finishActiveJob(), finishActiveAttack(), energyRefresher()).done(function(d1, d2, d3){
		profileRefresher();
		statusRefresher();
		skillsRefresher();
		jobsRefresher();
		messageRefresher();
	});
}

function profileRefresher(){
	$.ajax({
		type: "GET",
		url: "users/profile",
		success: function(data){
					$("#profile").empty();
					var profileData = "<p>Username: " + data.name + " Level: " + data.level +" Xp: " + data.xp + " Energy: " + data.energy + " Money: " + data.money + " Successful attacks: " + data.successfulAttacks + " Unsuccessful attacks: " + data.unsuccessfulAttacks + " Skillpoints: " + data.skillPoints + " </p>";
					$("#profile").append(profileData);
					var lastEnergy = parseDateFromJsonDate(data.lastEnergyRefresh).getTime();
					var timeToRefresh = lastEnergy + 3*60*1000 - new Date().getTime();
					clearTimeout(energyTimeout);
					energyTimeout = setTimeout(function(){
						$.when(energyRefresher()).done(function(d1){
							profileRefresher();
							jobsRefresher();
						});
					}, timeToRefresh);
		}
	});
}

function statusRefresher(){
	$("#status").empty();
	getActiveJob();
	getActiveAttack();
}

function skillsRefresher(){
	$("#skills").empty();
	$.ajax({
		type: "GET",
		url: "skills/",
		success: function(data){
			var skillsList = $("<ul></ul>");
			data.forEach(function(element){
				var skill = "<li> Name: " + element.name + " Available: " + element.available + " Learnt: " + element.learnt + "</li>";
				var learnButton;
				if(element.available){
					learnButton = $("<button>learn</button>").attr("onclick", "learnSkill(" + element.id + ")");
				} else {
					learnButton = $("<button disabled>learn</button>").attr("onclick", "learnSkill(" + element.id + ")");
				}
				skillsList.append(skill);
				skillsList.append(learnButton);
			});
			$("#skills").append(skillsList);
		}
	});
}

function jobsRefresher(){
	$("#jobs").empty();
	$.ajax({
		type: "GET",
		url: "jobs/",
		success: function(data){
			var jobsList = $("<ul></ul>");
			data.forEach(function(element){
				var job = "<li> Name: " + element.name + " Available: " + element.available + "</li>";
				var startJobButton;
				if(element.available){
					startJobButton = $("<button>work</button>").attr("onclick", "startJob(" + element.id + ")");
				} else {
					startJobButton = $("<button disabled>work</button>").attr("onclick", "startJob(" + element.id + ")");
				}
				jobsList.append(job);
				jobsList.append(startJobButton);
			});
			$("#jobs").append(jobsList);
		}
	});
}


function getActiveJob() {
	$.ajax({
		type: "GET",
		url: "jobs/active",
		success: function(data){
			if(data != ""){
				var jobData = "<p>You are currently working on: '" + data.name + "'</p>";
				$("#status").append(jobData);
				var timeLeft = getDifferenceInMilisec(parseDateFromJsonDate(data.finish), new Date());
				if(timeLeft < 0){
					finishActiveJob();
				} else {
					secondsLeft = Math.ceil(timeLeft / 1000);
					interval = setInterval(updateCounter,1000);
					setTimeout(finishActiveJob, timeLeft + 1000);
				}
			}
		}
	});
}

function finishActiveJob() {
	return $.ajax({
		type: "POST",
		url: "jobs/finish",
		success: function(data){
			if(data != ""){
				statusRefresher();
				receivedMessageRefresher();
			}
		}
	});
}

function getActiveAttack() {
	$.ajax({
		type: "GET",
		url: "attack/active",
		success: function(data){
			if(data != ""){
				var attackData = "<p>You are currently attacking: '" + data.defenderName + "'</p>";
				$("#status").append(attackData);
				var timeLeft = getDifferenceInMilisec(parseDateFromJsonDate(data.finish), new Date());
				if(timeLeft < 0){
					finishActiveAttack();
				} else {
					secondsLeft = Math.ceil(timeLeft / 1000);
					interval = setInterval(updateCounter,1000);
					setTimeout(finishActiveAttack, timeLeft + 1000);
				}
			}
		}
	});
}

function finishActiveAttack() {
	return $.ajax({
		type: "POST",
		url: "attack/finish",
		success: function(data){
			if(data != ""){
				statusRefresher();
				receivedMessageRefresher();
			}
		}
	});
}

function updateCounter() {
	if(Math.round(secondsLeft) <= 0){
		$("#statusCounter").empty();
		profileRefresher();
		skillsRefresher();
		jobsRefresher();
		clearInterval(interval);
	} else {
		$("#statusCounter").empty();
		var seconds = parseInt((secondsLeft)%60);
		var minutes = parseInt((secondsLeft/(60))%60);
		var hours = parseInt((secondsLeft/(60*60))%24);
		/*var tmp = secondsLeft;
		var hours = Math.floor(tmp / 3600);
		tmp -= hours * 3600;
		var minutes = Math.floor(tmp / 60);
		tmp -= minutes * 60;
		var seconds = Math.round(tmp);*/
		secondsLeft--;
		$("#statusCounter").append("<p>Time left: Hours: " + hours + " Minutes: " + minutes + " Seconds: " + seconds + "</p>");
	}
}

function parseDateFromJsonDate(jsonDate) {
	return new Date(jsonDate.year, jsonDate.monthValue - 1, jsonDate.dayOfMonth, jsonDate.hour, jsonDate.minute, jsonDate.second + 1, 0);
}

function getDifferenceInMilisec(finish, now) {
	return finish.getTime() - now.getTime();
}

function learnSkill(id) {
	$.ajax({
		type: "POST",
		url: "skills/learn/" + id,
		success: function(data){
			if(data != ""){
				profileRefresher();
				skillsRefresher();
				jobsRefresher();
			}
		}
	});
}

function startJob(id) {
	$.ajax({
		type: "POST",
		url: "jobs/start/" + id,
		success: function(data){
			if(data != ""){
				profileRefresher();
				statusRefresher();
				jobsRefresher();
			}
		}
	});
}

function energyRefresher() {
	return $.ajax({
		type: "POST",
		url: "energy/regenerate",
		success: function(data){
		}
	});
}

function messageRefresher() {
	$("#messages").empty();
	$("#messages").append("<button onclick=\"messageRefresher()\">refresh messages</button><br/>");
	$("#messages").append("<label>Received messages</label>");
	$("#messages").append("<div id=\"received\"></div>");
	$("#messages").append("<label>Sent messages</label>");
	$("#messages").append("<div id=\"sent\"></div>");
	receivedMessageRefresher();
	sentMessageRefresher();
}

function receivedMessageRefresher() {
	return $.ajax({
		type: "GET",
		url: "messages/",
		success: function(data){
			console.log(data);
			var receivedMessages = $("<ul></ul>");
			data.forEach(function(message){
				var receivedMessage = "<li>Sender: " + message.senderName + " Type: " + message.type + " Subject: " + message.subject + " Date: " + parseDateFromJsonDate(message.date) + " Read: " + message.read + "</li>"
				var openButton = $("<button>open</button>").attr("onclick", "openMessage(" + message.id +")");
				receivedMessages.append(receivedMessage);
				receivedMessages.append(openButton);
			});
			$("#received").append(receivedMessages);
		}
	});
}

function openMessage(id) {
	return $.ajax({
		type: "GET",
		url: "messages/" + id,
		success: function(data){
			if(data != "") {
				$("#messages").empty();
				$("#messages").append("<button onclick=\"messageRefresher()\">back</button>");
				$("#messages").append("<div>Sender: " + data.senderName + "</div>");
				$("#messages").append("<div>Receiver: " + data.receiverName + "</div>");
				$("#messages").append("<div>Type: " + data.type + "</div>");
				$("#messages").append("<div>Date: " + parseDateFromJsonDate(data.date) + "</div>");
				$("#messages").append("<div>Subject: " + data.subject + "</div>");
				$("#messages").append("<div>Message: " + data.message + "</div>");
			}
		}
	});
}

function sentMessageRefresher() {
	return $.ajax({
		type: "GET",
		url: "messages/sent",
		success: function(data){
			console.log(data);
			var sentMessages = $("<ul></ul>");
			data.forEach(function(message){
				var sentMessage = "<li>Sender: " + message.receiverName + " Type: " + message.type + " Subject: " + message.subject + " Date: " + parseDateFromJsonDate(message.date) + "</li>"
				var openButton = $("<button>open</button>").attr("onclick", "openMessage(" + message.id +")");
				sentMessages.append(sentMessage);
				sentMessages.append(openButton);
			});
			$("#sent").append(sentMessages);
		}
	});
}