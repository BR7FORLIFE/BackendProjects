import { Injectable } from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { Strategy, ExtractJwt } from 'passport-jwt';
import { PUBLIC_KEY_JWT } from '../../../constants.js';

export interface Payload {
  sub: string;
  rols: string[];
  userId: string;
}

@Injectable()
export class JwtStrategyService extends PassportStrategy(
  Strategy,
  'jwt-strategy',
) {
  constructor() {
    super({
      jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
      secretOrKey: PUBLIC_KEY_JWT,
      algorithms: ['RS256'],
    });
  }

  validate(payload: Payload): Payload {
    return {
      userId: payload.userId,
      rols: payload.rols,
      sub: payload.sub,
    };
  }
}
