const fetchEndPoint = async (url, httpData) => {
    return fetch(url, httpData)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error ' + response.status)
            }
            return response.json()
        })
        .catch(error => {
            console.error('Echec du fetch ', error)
            throw error
        })
}

export default fetchEndPoint;