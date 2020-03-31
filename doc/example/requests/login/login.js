const request = require('../request.js')

request({
    method: 'POST',
    url: '/login',
    data: {
        "telephone": "12334554433",
        "password": "AAaa11"
    }
})
