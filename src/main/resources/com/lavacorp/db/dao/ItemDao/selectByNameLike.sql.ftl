<#include "select.sql.ftl">
WHERE item.name LIKE "%" || :name || "%" COLLATE NOCASE
