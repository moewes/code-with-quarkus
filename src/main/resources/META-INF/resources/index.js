import './opendxp-link.js';
import './dummy-ws.js';
import {Router} from 'https://unpkg.com/@vaadin/router';

window.addEventListener('load', ()=> {
  fetchLinks();
  setupRouter();
});

async function setupRouter() {
  const outlet = document.getElementById('outlet');
  const router = new Router(outlet);
  router.setRoutes([
    {path: '/portal', component: 'dummy-one'},
    {path: '/portal/users', component: 'dummy-two'},
    {path: '/users/:user', component: 'x-user-profile'},
  ]);
}

async function fetchLinks() {

  const res = await fetch("./links");
  const json = await res.json();

  const main = document.querySelector('main');

  json.forEach( link => {
    const el = document.createElement('opendxp-link');
    el.link = link;
    main.appendChild(el);
    });
}