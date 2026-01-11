import js from '@eslint/js'
import globals from 'globals'
import reactHooks from 'eslint-plugin-react-hooks'
import reactRefresh from 'eslint-plugin-react-refresh'
import tseslint from 'typescript-eslint'
import {defineConfig, globalIgnores} from 'eslint/config'
import reactX from 'eslint-plugin-react-x'
import reactDom from 'eslint-plugin-react-dom'

export default defineConfig([
    globalIgnores(['dist']),
    {
        files: ['**/*.{ts,tsx}'],
        extends: [
            js.configs.recommended,
            tseslint.configs.recommendedTypeChecked,
            // tseslint.configs.strictTypeChecked, // TODO: try it some day, seems pretty strict
            tseslint.configs.stylisticTypeChecked,

            reactX.configs['recommended-typescript'],
            reactDom.configs.recommended,
        ],
        languageOptions: {
            ecmaVersion: 2020,
            globals: globals.browser,

            parserOptions: {
                project: ['./tsconfig.node.json', './tsconfig.app.json'],
                tsconfigRootDir: import.meta.dirname,
            },
        },
        plugins: {
            'react-hooks': reactHooks,
            'react-refresh': reactRefresh,
        },
        rules: {
            "indent": ["error", 2],
            "quotes": ["error", "double"],
            ...reactHooks.configs.recommended.rules,
            'react-refresh/only-export-components': [
                'warn',
                {allowConstantExport: true},
            ],
            '@typescript-eslint/no-unsafe-call': 'off',
            '@typescript-eslint/no-unused-vars': 'off',
            '@typescript-eslint/no-unsafe-member-access': 'off',
            '@typescript-eslint/prefer-promise-reject-errors': 'off', // TODO: 'npx eslint .' and fix
        },
    },
])
