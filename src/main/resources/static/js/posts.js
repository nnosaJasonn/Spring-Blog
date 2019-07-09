(function($) {

    var request = $.ajax({
        'url': '/posts.json',
        });
    request.done(function (posts) {

        console.log(posts);
        var html = '';
        posts.forEach(function(post) {
            html += '<div class="my-4 card">';
            html += '<h3 class="card-header">' + post.title + '</h3>';
            html += `<p class="ml-4">Author:  <a href="/users/${post.author.username}">${post.author.email}</a></p>`;
            html += '<pre><p class="card-body">' + post.body + '</p></pre>';
            html += `<p class="ml-4"> Tags:`;
            post.categories.forEach(function(category){
                html +=`<a href="/categories/${category.id}"><span class="mx-1"><small>${category.title}</span></small></a>`;
            });
            html+= '</p>';
            html+= `<form method="post" action="/posts"><input name="post" value="${post.id}" type="hidden"><button type="submit">View Post</button></form>`;
            html += '</div>';
        });
        $('#posts').html(html);
    });
})(jQuery);

