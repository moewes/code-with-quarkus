import "/opendxp-container.js"

class DummyOne extends HTMLElement {

    constructor() {
        super();
        this.root = this.attachShadow({mode: 'open'});
        this.root.innerHTML = `<div>Dummy One</div>`;
    }

    set link(link) {
        this.innerHTML = `<p>link ${link}</p>`;
    }
}

customElements.define('dummy-one',DummyOne);

class DummyTwo extends HTMLElement {

    constructor() {
        super();
        this.root = this.attachShadow({mode: 'open'});
        this.root.innerHTML = `<div>Dummy Two</div><opendxp-container>
        <p>hallo Container</p>
        <p>Tschüß Container</p>
        </opendxp-container>`;
    }

    set link(link) {
        this.innerHTML = `<p>link ${link}</p>`;
    }
}

customElements.define('dummy-two',DummyTwo);