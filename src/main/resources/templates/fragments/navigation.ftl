<#import "/spring.ftl" as spring/>
<#--<#assign security=JspTaglibs["/tld/security.tld"] /> fixme - security does not work!!!-->
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
                <li class="active">
                    <a href="<@spring.url '/'/>">${springMacroRequestContext.getMessage('page.nav.home')}</a>
                </li>
                <li>
                    <a href="<@spring.url '/medrecords'/>">${springMacroRequestContext.getMessage('page.nav.medRecords')}</a>
                </li>
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
            <#--<@security.authorize ifAnyGranted="USER">-->
            <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"-->
            <#--aria-expanded="false">loged in user<span-->
            <#--class="caret"></span></a>-->
            <#--<ul class="dropdown-menu">-->
            <#--<li><a href="<@spring.url '/logout'/>">Logout</a></li>-->
            <#--</ul>-->
            <#--</@security.authorize>-->
            <#--<@security.authorize ifNotGranted="USER">-->
                <li><a href="<@spring.url '/login'/>">${springMacroRequestContext.getMessage('page.nav.login')}&nbsp;&nbsp;<span
                        class="glyphicon glyphicon-log-in"></span></a></li>
            <#--</@security.authorize>-->
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>