import {
  Body,
  Controller,
  Post,
  Req,
  UseFilters,
  UseGuards,
} from '@nestjs/common';
import { JwtAuthGuard } from '../../../auth/guards/auth.guard.js';
import { GenerateProductRequest } from '../../application/dto/generate-product-request.dto.js';
import { ProductUseCaseImp } from '../../application/orchestator/product-orchestator.js';
import { AuthExceptionFilter } from '../../../auth/filters/auth-exception-filter.js';
import { UserNotFound } from '../../../auth/exceptions/user-not-found.js';
import type { AuthRequest } from '../../../auth/context/auth-request.js';

@UseFilters(AuthExceptionFilter)
@UseGuards(JwtAuthGuard)
@Controller({
  version: '1',
  path: 'products',
})
export class ProductsController {
  constructor(private productUseCase: ProductUseCaseImp) {}

  @Post()
  async generateProduct(
    @Req() req: AuthRequest,
    @Body() generateProductDto: GenerateProductRequest,
  ) {
    const userId = req.user.userId;

    if (!userId) {
      throw new UserNotFound();
    }

    const saved = await this.productUseCase.generateProduct({
      userId,
      name: generateProductDto.name,
      quantity: generateProductDto.quantity,
      sku: generateProductDto.sku,
    });

    return saved;
  }
}
