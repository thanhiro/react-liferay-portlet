const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const SRC = path.resolve(__dirname, 'src/main/js');
const DEST = path.resolve(__dirname, 'src/main/resources/META-INF/resources/dist');

module.exports = {
  entry: {
    app: SRC + '/client.js'
  },
  resolve: {
    extensions: ['.js','.jsx']
  },
  output: {
    path: DEST,
    filename: '[name].js',
    chunkFilename: '[id].[chunkhash].js'
  },
  module: {
    loaders: [
      {
        test: /\.jsx?$/,
        loaders: ['babel-loader'],
        include: SRC
      },
      {test: /\.css$/, loader: 'style-loader!css-loader'},
      {test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&amp;mimetype=application/font-woff'},
      {test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&amp;mimetype=application/octet-stream'},
      {test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: 'file'},
      {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&amp;mimetype=image/svg+xml'}
    ]
  },
  plugins: [
    new webpack.ProvidePlugin({
      AUI: 'AUI',
      Liferay: 'Liferay'
    }),
    new webpack.DefinePlugin({
      // env: JSON.stringify('production'),
      __SSR__: false
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
