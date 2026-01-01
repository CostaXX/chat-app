'use strict';

const { use } = require("react");

let stompClient = null;
let currentUser = null;
let isConnected = false;
let unreadCount = 0;
let isWindowFocused = true;
let typingTimer = null;

const userData = document.getElementById('currentUser');
currentUser = {
    username: userData.dataset.username,
    avatarColor: userData.dataset.color
}

window.addEventListener('focus', () => {
    isWindowFocused = true;
});

window.addEventListener('blur', () => {
    isWindowFocused = false;
});

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

function onError(error){
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!', error);
}

function onConnected() {
    console.log('Connected to WebSocket server.');
    isConnected = true;

    stompClient.subscribe('/topic/public', onMessageReceived);

    stompClient.send("/app/chat.addUser", {},JSON.stringify({
        username: currentUser.username,
        avatarColor: currentUser.avatarColor,
        message: "Connected!"
    }))
}

connect();

document.getElementById('messageInput').focus();