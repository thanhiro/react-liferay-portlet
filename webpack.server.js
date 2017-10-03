const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const SRC = path.resolve(__dirname, 'src/main/js');
const DEST = path.resolve(__dirname, 'src/main/resources/META-INF/resources/dist');

module.exports = {
  entry: {
    server: SRC + '/server.js'
  },
  resolve: {
    extensions: ['.js','.jsx']
  },
  output: {
    path: DEST,
    filename: '[name].js',
    chunkFilename: '[id].[chunkhash].js',
    library: "renderApp",
    libraryTarget: 'umd'
  },
  module: {
    loaders: [
      {
        test: /\.jsx?$/,
        loaders: ['babel-loader'],
        include: SRC
      }
    ]
  },
  plugins: [
    new webpack.ProvidePlugin({
      AUI: 'AUI',
      Liferay: 'Liferay'
    }),
    new webpack.DefinePlugin({
      // env: JSON.stringify('production'),
      __SSR__: true
    })
    /*
    new HtmlWebpackPlugin({
      template: './src/main/resources/app/index.html',
      filename: 'index.html',
      inject: 'body'
    })
    */
    /*new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      minChunks: function (module) {
        // this assumes your vendor imports exist in the node_modules directory
        return module.context && module.context.indexOf("node_modules") !== -1;
      }
    })*/
  ]
};
