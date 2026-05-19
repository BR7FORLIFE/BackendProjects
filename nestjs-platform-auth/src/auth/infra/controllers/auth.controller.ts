import {
  Body,
  Controller,
  Get,
  Inject,
  Post,
  Query,
  Req,
  Res,
} from '@nestjs/common';
import type { AuthUseCase } from '../../application/usecases/auth.usecase';
import type { Request, Response } from 'express';
import type { registerUserDto } from '../../application/dtos/register-user.dto';

@Controller({
  path: 'auth',
  version: '1',
})
export class AuthController {
  constructor(
    @Inject('AUTH_USECASE')
    private readonly auth: AuthUseCase,
  ) {}

  @Post('register')
  async register(@Body() register: registerUserDto) {
    this.auth.register(register);
  }

  //contraoladores de oath2
  @Get('google')
  googleAuth(@Res() res: Response) {
    const { redirectUrl, state } = this.auth.initializerOAuth2();

    res.cookie('oauth_state', state, {
      httpOnly: true,
      secure: false,
      sameSite: 'lax',
    });

    res.redirect(redirectUrl);
  }

  @Get('google/redirect')
  googleAuthRedirect(
    @Req() req: Request,
    @Query('code') code,
    @Query('state') state,
  ) {
    const oldState = req.cookies?.oauth_state;

    if (!oldState) {
      throw new Error('missing state value!');
    }
  }
}
