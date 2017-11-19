<#import "/spring.ftl" as spring/>

<#macro authorize ifAnyGranted>
    <#assign authorized = false>
    <#list (authentication.authorities)!['ROLE_ANONYMOUS'] as authority>
        <#if authority == ifAnyGranted>
            <#assign authorized = true>
        </#if>
    </#list>
    <#if authorized>
        <#nested>
    </#if>
</#macro>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"
               href="<@spring.url '/'/>">${springMacroRequestContext.getMessage('organization.name')}</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li id="home">
                    <a href="<@spring.url '/'/>">${springMacroRequestContext.getMessage('page.nav.home')}</a>
                </li>
            <@authorize ifAnyGranted="ROLE_USER">
                <li id="medrecords">
                    <a href="<@spring.url '/medrecord/all'/>">${springMacroRequestContext.getMessage('page.nav.medRecords')}</a>
                </li>
            </@authorize>
            <@authorize ifAnyGranted="ROLE_OPERATOR">
                <li id="users">
                    <a href="<@spring.url '/users'/>">${springMacroRequestContext.getMessage('page.nav.users')}</a>
                </li>
            </@authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">${springMacroRequestContext.getMessage('page.nav.language')}<span
                            class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<@spring.url '/?language=ru'/>">Русский</a></li>
                        <li><a href="<@spring.url '/?language=en'/>">English</a></li>
                    </ul>
                </li>
            <@authorize ifAnyGranted="ROLE_USER">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">${authentication.principal.displayName}<span
                            class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<@spring.url '/logout'/>">${springMacroRequestContext.getMessage('page.nav.logout')}</a>
                        </li>
                    </ul>
                </li>
            </@authorize>
            <@authorize ifAnyGranted="ROLE_ANONYMOUS">
                <li><a href="<@spring.url '/login'/>">${springMacroRequestContext.getMessage('page.nav.login')}&nbsp;&nbsp;<span
                        class="glyphicon glyphicon-log-in"></span></a></li>
            </@authorize>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>