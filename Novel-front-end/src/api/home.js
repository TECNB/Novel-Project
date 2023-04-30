import request from '../utils/request'

export function listHomeBooks() {
    return request.get('/front/home/books');
}


export function listHomeCategory() {
    return request.get('/front/home/category');
}

export function listHomeCategory2() {
    return request.get('/front/home/category2');
}
export function listHomeCategory3() {
    return request.get('/front/home/category3');
}
export function listHomeCategory4() {
    return request.get('/front/home/category4');
}
export function listHomeCategoryId(categoryId) {
    return request.get(`/front/home/${categoryId}`);
}
