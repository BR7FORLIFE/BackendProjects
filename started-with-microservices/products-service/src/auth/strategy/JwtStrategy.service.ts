import { Injectable } from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { Strategy, ExtractJwt } from 'passport-jwt';
import { PUBLIC_KEY_JWT } from '../../../constants';

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
      secretOrKey: PUBLIC_KEY_JWT!,
      algorithms: ['RS256'],
    });
  }

  async validate(payload: Payload): Promise<Payload> {
    return new Promise((resolve, _) => {
      resolve({
        userId: payload.userId,
        rols: payload.rols,
        sub: payload.sub,
      });
    });
  }
}
