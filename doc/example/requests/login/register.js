const request = require('../request.js')

request({
    method: 'POST',
    url: '/register',
    data: {
        "nickname": "Melody",
        "telephone": "12334554433",
        "password": "AAaa11"
    }
})
