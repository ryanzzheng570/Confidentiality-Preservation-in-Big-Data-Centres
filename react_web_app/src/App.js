import React, { Component } from 'react';
import { connect } from 'react-redux';

import logo from './logo.svg';
import './App.css';

//import needed actions type
import { sampleAction } from './actions/sampleAction';


class App extends Component {

  constructor(props) {
    super(props);
  }

  sampleAction = (e) => {
  this.props.sampleAction();
}

  render() {
    return (
      <div>
      <pre>{JSON.stringify(this.props)}(</pre>
      <button onClick={this.sampleAction} >Test redux actions</button>
      </div>
    )
  };
}

const mapStateToProps = state => ({
  ...state
})

const mapDispatchToProps = dispatch => ({
  sampleAction: () => dispatch(sampleAction())
})

export default connect(mapStateToProps, mapDispatchToProps)(App);
