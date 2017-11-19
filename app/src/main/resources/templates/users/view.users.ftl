<#import "../fragments/page.master.ftl" as layout/>
<#include "../fragments/page.master.ftl"/>

<#assign layoutOut>
<script type="application/javascript">
    $("#users").addClass('active');
</script>

<form method="post" action="/users">
    <@spring.bind "Users"/>
    <@spring.bind "SearchCriteria"/>

    <div class="page-header">
        <h2><@spring.message "page.header.users"/></h2>
    </div>

    <div class="row">
    <#-- Search -->
        <div class="panel-group">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse"
                           href="#collapseSearch"><@spring.message "page.button.search"/></a>
                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-refresh"></span>
                        </button>
                    </h4>
                </div>
                <div id="collapseSearch" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="patient"
                                   class="col-sm-3 control-label"><@spring.message "page.header.username"/></label>
                            <div class="col-sm-9">
                                <@spring.formInput "SearchCriteria.fullTextField1" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.user')}'" "text"/><@spring.showErrors "<br>"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="pull-right">
            <a href="/user">
                <button type="button"
                        class="btn btn-primary"><@spring.message "page.button.user.register"/></button>
            </a>
        </div>
    </div>

<#-- Result table -->
    <div class="row">
        <div class="col-md-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><@spring.message "page.field.user.name"/></th>
                        <th><@spring.message "page.field.user.displayName"/></th>
                        <th><@spring.message "page.field.user.role"/></th>
                        <th><@spring.message "page.actions"/></th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list Users>
                            <#items as user>
                            <tr>
                                <td>${user?counter}</td>
                                <td>${user.username}</td>
                                <td>${user.displayName}</td>
                                <td><@spring.message "page.field.role."+user.role/></td>
                                <td>
                                    <a href="/user/load/${(user.id)}/view">
                                        <button type="button" class="btn btn-xs btn-default">
                                            <span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;<@spring.message "page.button.view"/>
                                        </button>
                                    </a>
                                    <a href="/user/load/${(user.id)}/edit">
                                        <button type="button" class="btn btn-xs btn-success">
                                            <span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;<@spring.message "page.button.edit"/>
                                        </button>
                                    </a>
                                <@authorize ifAnyGranted="ROLE_ADMIN">
                                    <button type="button" class="btn btn-xs btn-danger"
                                            onclick="this.disabled=true;post('/user/${user.id}/delete', {'${_csrf.parameterName}':'${_csrf.token}'})">
                                        <span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;<@spring.message "page.button.delete"/>
                                    </button>
                                </@authorize>
                                </td>
                            </tr>
                            </#items>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
<#--    <div class="row">
        <#if Users?size gt 0>
            <ul class="pagination">
                <li>
                    <a href="#">Prev</a>
                </li>
                <li>
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">Next</a>
                </li>
            </ul>
        </#if>
    </div>-->

    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

</#assign>

<@layout.indexmaster  nestedOut="${layoutOut}" titleKey="page.title.users"/>