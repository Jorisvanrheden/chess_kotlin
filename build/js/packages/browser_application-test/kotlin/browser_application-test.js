(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'kotlin-test'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('kotlin-test'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'browser_application-test'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'browser_application-test'.");
    }
    if (typeof this['kotlin-test'] === 'undefined') {
      throw new Error("Error loading module 'browser_application-test'. Its dependency 'kotlin-test' was not found. Please, check whether 'kotlin-test' is loaded prior to 'browser_application-test'.");
    }
    root['browser_application-test'] = factory(typeof this['browser_application-test'] === 'undefined' ? {} : this['browser_application-test'], kotlin, this['kotlin-test']);
  }
}(this, function (_, Kotlin, $module$kotlin_test) {
  'use strict';
  var assertEquals = $module$kotlin_test.kotlin.test.assertEquals_3m0tl5$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var test = $module$kotlin_test.kotlin.test.test;
  var suite = $module$kotlin_test.kotlin.test.suite;
  function TestClient() {
  }
  TestClient.prototype.testGreet = function () {
    assertEquals('', '');
  };
  TestClient.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TestClient',
    interfaces: []
  };
  _.TestClient = TestClient;
  suite('', false, function () {
    suite('TestClient', false, function () {
      test('testGreet', false, function () {
        return (new TestClient()).testGreet();
      });
    });
  });
  Kotlin.defineModule('browser_application-test', _);
  return _;
}));

//# sourceMappingURL=browser_application-test.js.map
