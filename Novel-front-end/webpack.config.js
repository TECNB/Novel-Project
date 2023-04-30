//引入vue-loader/lib/plugin
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
    plugins: [
        new VueLoaderPlugin()
    ],
    module: {
        rules: [
            {
                test: /\.vue$/,
                use: {
                    loader: 'vue-loader'
                }
            },
        ]
    }
};

