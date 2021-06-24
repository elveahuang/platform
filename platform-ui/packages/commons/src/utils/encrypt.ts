import { trim } from 'lodash';
import CryptoJS from 'crypto-js';

const KEY = '1234567812345678';
const IV = '1234567812345678';
const CryptoJS_KEY = CryptoJS.enc.Utf8.parse(KEY);
const CryptoJS_IV = CryptoJS.enc.Utf8.parse(IV);
const CryptoJS_Cipher_Option = { iv: CryptoJS_IV, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 };

export function encrypt(text: string): string {
    const encrypted = aesEncrypt(text);
    return CryptoJS.enc.Hex.stringify(CryptoJS.enc.Utf8.parse(encrypted));
}

export function decrypt(text: string): string {
    const encrypted = CryptoJS.enc.Utf8.stringify(CryptoJS.enc.Hex.parse(text));
    return aesDecrypt(encrypted);
}

/**
 * AES Encrypt
 */
export function aesEncrypt(text: string): string {
    const encrypted = CryptoJS.AES.encrypt(trim(text), CryptoJS_KEY, CryptoJS_Cipher_Option);
    return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
}

/**
 * AES Decrypt
 */
export function aesDecrypt(text: string): string {
    const cipher = CryptoJS.lib.CipherParams.create({
        ciphertext: CryptoJS.enc.Base64.parse(text),
        key: CryptoJS_KEY,
    });
    const decrypted = CryptoJS.AES.decrypt(cipher, CryptoJS_KEY, CryptoJS_Cipher_Option);
    return decrypted.toString(CryptoJS.enc.Utf8);
}
