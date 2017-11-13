<#import "../fragments/page.master.ftl" as layout/>
<#include "../fragments/page.master.ftl"/>

<#assign layoutOut>
<script type="application/javascript">
    $("#users").addClass('active');
</script>

<form id="User" class="form-horizontal" method="post" action="/user" modelAttribute="User">
    <@spring.bind "User"/>
    <@spring.bind "Sexes"/>
    <@spring.bind "Countries"/>
    <@spring.bind "Roles"/>
    <@spring.bind "Specialities"/>

    <div class="page-header">
        <#if ((User.id!"")?length > 0) >
            <h2><@spring.message "page.header.user.edit"/></h2>
        <#else>
            <h2><@spring.message "page.header.user.new"/></h2>
        </#if>

    </div>

    <div class="row">
    <#--Person-->
        <div class="col-md-6">
        <#--Personal Info -->
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><@spring.message "page.header.user"/></h3>
                </div>
                <div class="panel-body">
                    <@spring.formHiddenInput "User.id"/>
                    <@spring.formHiddenInput "User.version"/>
                    <div class="form-group">
                        <label for="username"
                               class="col-sm-3 control-label">${springMacroRequestContext.getMessage('page.field.username')}</label>
                        <div class="col-sm-9">
                            <@spring.formInput "User.username" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.username')}' required autofocus" "text"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password"
                               class="col-sm-3 control-label">${springMacroRequestContext.getMessage('page.field.password')}</label>
                        <div class="col-sm-9">
                            <#if ((User.id!"")?length > 0) >
                                <#assign passReq=""/>
                            <#else>
                                <#assign passReq="required"/>
                            </#if>
                            <@spring.formInput "User.password" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.password')}' ${passReq}" "password"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="role"
                               class="col-sm-3 control-label"><@spring.message "page.field.user.role"/></label>
                        <div class="col-sm-9">
                            <@spring.formSingleSelect "User.role", Roles, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.user.role')}' required">
                            </@spring.formSingleSelect>
                            <@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="role"
                               class="col-sm-3 control-label"><@spring.message "page.field.worker.speciality"/></label>
                        <div class="col-sm-9">
                            <@spring.formSingleSelect "User.speciality", Specialities, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.worker.speciality')}'">
                            </@spring.formSingleSelect>
                            <@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <label for="firstName"
                               class="col-sm-3 control-label"><@spring.message "page.field.person.firstName"/></label>
                        <div class="col-sm-9">
                            <@spring.formInput "User.firstName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.firstName')}'" "text"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastName"
                               class="col-sm-3 control-label"><@spring.message "page.field.person.lastName"/></label>
                        <div class="col-sm-9">
                            <@spring.formInput "User.lastName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.lastName')}'" "text"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="middleName"
                               class="col-sm-3 control-label"><@spring.message "page.field.person.middleName"/></label>
                        <div class="col-sm-9">
                            <@spring.formInput "User.middleName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.middleName')}'" "text"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="birthDate"
                               class="col-sm-3 control-label"><@spring.message "page.field.person.birthDate"/></label>
                        <div class="col-sm-9">
                            <@spring.formInput "User.birthDate" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.birthDate')}'" "date"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sex"
                               class="col-sm-3 control-label"><@spring.message "page.field.person.sex"/></label>
                        <div class="col-sm-9">
                            <@spring.formSingleSelect "User.sex", Sexes, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.person.sex')}'">
                            </@spring.formSingleSelect>
                            <@spring.showErrors "<br>"/>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    <#--End Person-->
    </div>

<#-- Controls -->
    <div class="row">
        <div class="col-md-6">
            <div class="pull-right">
                <a href="/users">
                    <button type="button"
                            class="btn btn-sm btn-danger"><@spring.message "page.button.cancel"/></button>
                </a>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <#if !readOnlyForm>
                    <button type="submit"
                            class="btn btn-success"><@spring.message "page.button.save"/></button>
                </#if>
            </div>
        </div>
    </div>

<#-- Read only -->
    <@spring.bind "readOnlyForm"/>
    <input type="hidden"
           id="readOnlyForm"
           value="${readOnlyForm?c}"/>
</form>

</#assign>

<#function title>
    <#if (User.id)??>
        <#return 'page.title.user.edit'/>
    <#else>
        <#return 'page.title.user.register'/>
    </#if>
</#function>

<@layout.indexmaster  nestedOut="${layoutOut}" titleKey="${title()}"/>