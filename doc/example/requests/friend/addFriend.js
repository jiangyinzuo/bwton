const request = require('../request.js')

request({
    method: 'POST',
    url: '/friend',
    data: {
        "userId": 1,
        "friendId": 2
    },
    authToken: 'MTo4ZWFiYWJlMzJiYWVkMjIyZTcxMGRmYTVkMDc4NWU0NA=='
})