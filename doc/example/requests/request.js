const axios = require('axios');

const URL_PREFIX = 'http://localhost:8089/api/v1.0';

function request({method, url, data = null, params = null, authToken = null}) {
    axios({
        method,
        url: URL_PREFIX + url,
        data,
        params,
        headers: {
            Authorization: 'Bearer ' + authToken
        }
    },)
    .then(res => {
        console.log(res.status)
        console.log(res.data)
    })
    .catch(err => {
        const response = err.response;
        console.log(response.status)
        console.log(response.data)
    })
}

module.exports = request
