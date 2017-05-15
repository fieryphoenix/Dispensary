<#import "./fragments/page.master.ftl" as layout/>
<#include "./fragments/page.master.ftl"/>

<#macro page_head>
<link href="<@spring.url '/css/login.css'/>" rel="stylesheet">
</#macro>

<@layout.indexmaster title="Login">

<form class="form-signin" action="/login" method="post" modelAttribute="userForm">
    <h2 class="form-signin-heading">Please sign in</h2>
    <label for="login" class="sr-only">Username</label>
    <input type="text" id="login" class="form-control" placeholder="Login" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <div class="checkbox">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>

</@layout.indexmaster>