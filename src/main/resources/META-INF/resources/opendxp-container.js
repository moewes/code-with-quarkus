class OpenDxpContainer extends HTMLElement {

    constructor() {
        super();
        this.root = this.attachShadow({mode: 'open'});
        this.root.innerHTML = `<div><hr/><slot></slot><hr/></div>`;
    }

    set link(link) {
        this.innerHTML = `<p>link ${link}</p>`;
    }
}

customElements.define('opendxp-container',OpenDxpContainer);