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
import type { AuthUseCase } from '../../application/usecases/auth.usecase.js';
import type { Request, Response } from 'express';
import type { registerUserDto } from '../../application/dtos/register-user.dto.js';

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
    const { id, username } = await this.auth.register(register);

    return {
      id,
      username,
      message: 'user created succesfull!',
    };
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
    @Query('code') code: string,
    @Query('state') state: string,
  ) {
    const oldState: string | undefined = req.cookies.oauth_state;

    if (!oldState) {
      throw new Error('missing state value!');
    }
  }
}
