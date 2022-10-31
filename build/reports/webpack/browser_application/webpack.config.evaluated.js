{
  mode: 'production',
  resolve: {
    modules: [
      '/Users/jvanrheden/git/personal/kotlinjs/browser_application/build/js/packages/browser_application/kotlin-dce',
      'node_modules'
    ]
  },
  plugins: [
    ProgressPlugin {
      profile: false,
      handler: [Function: handler],
      modulesCount: 5000,
      dependenciesCount: 10000,
      showEntries: true,
      showModules: true,
      showDependencies: true,
      showActiveModules: false,
      percentBy: undefined
    },
    TeamCityErrorPlugin {}
  ],
  module: {
    rules: [
      {
        test: /\.js$/,
        use: [
          'source-map-loader'
        ],
        enforce: 'pre'
      },
      {
        test: /\.css$/,
        use: [
          {
            loader: 'style-loader',
            options: {}
          },
          {
            loader: 'css-loader',
            options: {}
          }
        ]
      }
    ]
  },
  entry: {
    main: [
      '/Users/jvanrheden/git/personal/kotlinjs/browser_application/build/js/packages/browser_application/kotlin-dce/browser_application.js'
    ]
  },
  output: {
    path: '/Users/jvanrheden/git/personal/kotlinjs/browser_application/build/distributions',
    filename: [Function: filename],
    library: 'browser_application',
    libraryTarget: 'umd',
    globalObject: 'this'
  },
  devtool: 'source-map',
  ignoreWarnings: [
    /Failed to parse source map/
  ],
  stats: {
    warnings: false,
    errors: false
  }
}