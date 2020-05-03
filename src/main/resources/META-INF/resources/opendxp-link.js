class OpenDxpLink extends HTMLElement {

    constructor() {
        super();
    }

    set link(link) {
        this.innerHTML = `<a href="${link.link}">${link.text}</a>`;
    }
}

customElements.define('opendxp-link',OpenDxpLink);