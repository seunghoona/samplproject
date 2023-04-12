export const DocumentUtils = {

    beforeEnd : (selector, html) => {
        insertAdjacentHtml(selector, "beforeend", getHtmlJoin(html));
    },

    afterEnd : (selector, html) => {
        insertAdjacentHtml(selector, "afterend", getHtmlJoin(html));
    },

    beforeBegin : (selector, html) => {
        insertAdjacentHtml(selector, "beforebegin", getHtmlJoin(html));
    },

    afterBegin : (selector, html) => {
        insertAdjacentHtml(selector, "afterbegin", getHtmlJoin(html));
    }

}

function isArray(enterHtml) {
    return Array.isArray(enterHtml)
}

function getHtmlJoin(enterHtml, delimiter = "") {
    return isArray(enterHtml) ? enterHtml.join(delimiter) : enterHtml
}

function insertAdjacentHtml(selector, position, html) {
    document.querySelector(selector).insertAdjacentHTML(position, html)
}