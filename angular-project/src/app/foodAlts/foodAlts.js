console.log("Hello world");

jQuery.ajax( {
    url: "http://api.walmartlabs.com/v1/search?query=bottled+water&format=json&apiKey=f9e8tfetpv3r64c6ewzjtkrt",
    type: 'GET',
    data: { content: 'testing test' },
    beforeSend : function( xhr ) {
        xhr.setRequestHeader( "X-Originating-Ip", "35.0.122.37");
    },
    success: function( response ) {
        // response
        console.log(response.Items[0].salePrice);
    }
} );