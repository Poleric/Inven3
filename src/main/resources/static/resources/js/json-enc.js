(function() {
    let api
    htmx.defineExtension('json-enc', {
        init: function(apiRef) {
            api = apiRef
        },

        onEvent: function(name, evt) {
            if (name === 'htmx:configRequest') {
                evt.detail.headers['Content-Type'] = 'application/json'
            }
        },

        encodeParameters: function(xhr, parameters, elt) {
            xhr.overrideMimeType('text/json')

            return (JSON.stringify(parameters))
        }
    })
})()
