const request = require('../request.js')

request({
    method: 'GET',
    url: '/leaderboard/total',
    params: {
        userId: 1
    },
    authToken: 'MTo4ZWFiYWJlMzJiYWVkMjIyZTcxMGRmYTVkMDc4NWU0NA=='
})