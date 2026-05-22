import { Injectable } from '@nestjs/common';
import type { ProductRepository } from '../../infra/repository/product.repository';

interface Product {
  userId: string;
  sku: string;
  name: string;
  quantity: number;
}

@Injectable()
export class ProductUseCaseImp {
  constructor(private productRepository: ProductRepository) {}

  async generateProduct(product: Product) {}
}
