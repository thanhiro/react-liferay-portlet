import './polyfill';
import React from 'react';
import { renderToString } from 'react-dom/server';
import StaticRouter from 'react-router-dom/StaticRouter';
import App from './app';

module.exports = function renderApp(path) {
  const html = renderToString(
    <StaticRouter basename="/ui" location={path} >
      <App />
    </StaticRouter>
  );
  return html;
};
