import { Body, Controller, Post, Req, UseGuards } from '@nestjs/common';
import type { Request } from 'express';
import { JwtAuthGuard } from '../../../auth/guards/auth.guard';
import type { GenerateProductRequest } from '../../application/dto/generate-product-request.dto';
import type { ProductUseCaseImp } from '../../application/orchestator/product-orchestator';

@UseGuards(JwtAuthGuard)
@Controller({
  version: '1',
  path: 'products',
})
export class ProductsController {
  constructor(private productUseCase: ProductUseCaseImp) {}

  @Post()
  async generateProduct(
    @Req() req: Request,
    @Body() generateProductDto: GenerateProductRequest,
  ) {
    const userId = req.user?.userId;

    if (!userId) {
      throw new Error('');
    }

    await this.productUseCase.generateProduct({
      userId,
      name: generateProductDto.name,
      quantity: generateProductDto.quantity,
      sku: generateProductDto.sku,
    });
  }
}
