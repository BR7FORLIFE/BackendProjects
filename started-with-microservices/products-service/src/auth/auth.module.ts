import { Module } from '@nestjs/common';
import { PassportModule } from '@nestjs/passport';
import { JwtModule } from '@nestjs/jwt';
import { JwtStrategyService } from './strategy/JwtStrategy.service';
import { PUBLIC_KEY_JWT } from '../../constants';

@Module({
  imports: [
    PassportModule,
    JwtModule.register({
      publicKey: PUBLIC_KEY_JWT,
      signOptions: {
        expiresIn: '15m',
      },
    }),
  ],
  providers: [JwtStrategyService],
})
export class AuthModule {}
