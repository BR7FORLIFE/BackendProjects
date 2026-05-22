import * as dotenv from 'dotenv';

dotenv.config();

export const PUBLIC_KEY_JWT = process.env.PUBLIC_KEY_JWT;

if (!PUBLIC_KEY_JWT) {
  throw new Error('Public key jwt is missing!');
}
