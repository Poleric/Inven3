function cascade_checkbox(source, tag) {
    const form = source.closest("form")
    const checkboxes = form.querySelectorAll(`:scope ${tag}`);
    for (let i = 0; i < checkboxes.length; i++)
        checkboxes[i].checked = source.checked
}

function close_modal_box(source) {
    const modal_box = source.closest(".modal");
    modal_box.style.display = "none";
}

function popup_delete() {
    const to_be_delete_table = document.querySelector("table.to-delete");
    to_be_delete_table.innerHTML = "";  // clear

    const table = document.querySelector("table.data-table");
    const thead = table.querySelector(":scope thead");
    const tbody = table.querySelector(":scope tbody");

    let rows = [];
    tbody.querySelectorAll(":scope tr").forEach(
        (row) => {
            let checkbox = row.querySelector("[name='selected']");
            if (checkbox.checked)
                rows.push(row);
        }
    )

    if (rows.length === 0)
        return;

    to_be_delete_table.append(thead.cloneNode(true));
    let to_be_delete_table_body = document.createElement("tbody");
    rows.forEach(
        (row) => {
            to_be_delete_table_body.append(row.cloneNode(true));
        }
    )
    to_be_delete_table.append(to_be_delete_table_body);

    document.querySelector('.modal.confirm-delete').style.display = 'flex';
}

