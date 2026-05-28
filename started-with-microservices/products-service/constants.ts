import * as dotenv from 'dotenv';
import * as fs from 'fs';

dotenv.config();

export const POSTGRES_URI = process.env.DATABASE_URI;

function obtainPublicKey() {
  const key = fs.readFileSync('./public.pem', 'utf-8'); //file system de docker al crear la imagen

  const parserKey = key
    .replace('-----BEGIN PUBLIC KEY-----', '')
    .replace('-----END PUBLIC KEY-----', '')
    .replaceAll('\\s', '');

  return parserKey;
}

export const PUBLIC_KEY_JWT = obtainPublicKey();
