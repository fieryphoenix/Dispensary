<#import "./fragments/page.master.ftl" as layout/>

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

<@layout.indexmaster titleKey="page.title.home">
    <@authorize ifAnyGranted="ROLE_ANONYMOUS">
        <div class="page-header">
            <h2>${springMacroRequestContext.getMessage('page.header.welcome')}</h2>
        </div>
    </@authorize>
    <script type="application/javascript">
        $("#home").addClass('active');
    </script>
</@layout.indexmaster>