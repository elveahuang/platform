export interface Result<T = any> {
    data: T;
    status: number;
    message: string;
}
