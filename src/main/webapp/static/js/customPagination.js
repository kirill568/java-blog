function simpleTemplating(data) {
    var html = '<div>';
    $.each(data, function(index, item){
        html += '<div>'+ item +'</div>';
    });
    html += '</div>';
    return html;
}

$('#pagination-container').pagination({
    dataSource: function(done){
	    var objs = Array.from(document.querySelectorAll(".data"));
	    var result = [];
	    for (var i = 0; i < objs.length; i++) {
	        let obj = objs[i];
	        result.push(obj.outerHTML);
	    }
	    done(result);
	 },
    pageSize: 10,
    ulClassName: "pagination",
    activeClassName: "active",
    callback: function(data, pagination) {
        // template method of yourself
        var html = simpleTemplating(data);
        $('#data-container').html(html);
    }
})