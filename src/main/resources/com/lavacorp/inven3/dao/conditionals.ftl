<#macro filter_id table operator id>
    ${table}.id ${operator} ${id}

</#macro>

<#macro filter_name table name>
    ${table}.name = ${name} COLLATE english_ci

</#macro>

<#macro filter_name_like table name_like>
    ${table}.name ILIKE '%' || ${name_like} || '%'

</#macro>

<#macro paginate table page page_size>
    LIMIT ${page_size}
    OFFSET ${(page - 1) * page_size}

</#macro>
