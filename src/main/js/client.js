import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, HashRouter } from 'react-router-dom'
import App from './app';

const useHistoryApi = window.__INITIAL_STATE__.config.historyApi === 'true';

const Router = useHistoryApi ? BrowserRouter : HashRouter;
const baseName = useHistoryApi ? '/ui' : null;

console.log("STARTING CLIENT");

ReactDOM.render(
  <Router basename={baseName}>
    <App />
  </Router>,
  document.getElementById("react-app")
);