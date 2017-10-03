import React from "react";
import styled from "styled-components";
import { Link, Route, Switch, withRouter } from 'react-router-dom'
import { observer } from 'mobx-react';
import { observable } from 'mobx';

const Button = styled.button`
  border-radius: 3px;
  padding: 0.25em 1em;
  margin: 0 1em;
  background: transparent;
  color: blue;
  border: 2px solid black;
`;

const store = observable({
  inputValue: ''
});

const Home = () => (
  <div>
    <h1>Home Sweet Home</h1>
    <p>
    </p>
  </div>
);

const Form = observer(() => (
  <div>
    <h1>The Form</h1>
    <p>{ store.inputValue }</p>
    <input
      value={store.inputValue}
      onChange={e => store.inputValue = e.target.value}
      type="text" />
    <Button>Button</Button>
  </div>
));

export default withRouter(props => {
  // Expose history to hosting Liferay. Hacky!
  if (!__SSR__) {
    window.appHistory =  props.history;
  }

  return (
    <div>
      <nav>
        <Link to="/">Home</Link><br />
        <Link to="/form">Form</Link><br />
      </nav>
      <div>
        <Switch>
          <Route exact path="/" component={Home} />
          <Route path="/form" component={Form} />
        </Switch>
      </div>
    </div>
  )
});