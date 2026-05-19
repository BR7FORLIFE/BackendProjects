import * as dotenv from 'dotenv';

dotenv.config();

//OAuth2 provider
export const OAUTH2_GOOGLE_CLIENT_ID = process.env.OAUTH2_GOOGLE_ID;
export const OAUTH2_GOOGLE_CLIENT_SECRET = process.env.OAUTH2_GOOGLE_SECRET;
export const OAUTH2_GOOGLE_PATH =
  'https://accounts.google.com/o/oauth2/v2/auth';
export const OAUTH2_GOOGLE_REDIRECT_URL =
  process.env.OAUTH2_GOOGLE_REDIRECT_URL;

//postgresql
export const POSTGRES_URI = process.env.POSTGRES_URI;
