import {Decoration} from "@/common/decoration/Decoration";
import {MetadataCenter} from "@/common/decoration/MetadataCenter";
import {axiosRequest} from "@/config/axios";
import {ListUtil} from "@/util/ListUtil";
import {ObjectUtil} from "@/util/ObjectUtil";

export const getBaseUrl = () => {
	let mock = import.meta.env.VITE_MOCK_ENABLE === "enable";
	let requestHost = import.meta.env.VITE_BACKEND_SERVER_URL;
	let mockUrl = "/mock";
	let baseUrl = `${requestHost}/api`;
	if (mock) {
		baseUrl = mockUrl;
	}
	return baseUrl;
}

export function useRequest<CLASS>(annotatedClass: Function): CLASS {
	const paramMetadataManager = MetadataCenter.getManager(annotatedClass)?.paramMetadataManager;
	const classMetadataManager = MetadataCenter.getManager(annotatedClass)?.classMetadataManager;
	const methodMetadataManager = MetadataCenter.getManager(annotatedClass)?.methodMetadataManager;

	// todo api 只需要单例模式即可, 没有必要反反复复的创建
	const instance: Function = new (annotatedClass as FunctionConstructor)();
	let classMetadata = classMetadataManager?.findAll(
		metadataDomain,
		annotatedClass.name,
		HttpMapping.name
	)?.[0];

	if (!classMetadata) {
		throw new Error(`[useRequest Error] @Request.HttpClient() annotation is required by ${annotatedClass.name}`)
	}

	const {basePath} = classMetadata.metadataValue as { basePath: string, clientName: string | undefined }

	const methodMetadataList = methodMetadataManager?.findAll(metadataDomain) ?? [];


	for (let methodMetadata of methodMetadataList) {

		if (methodMetadata.className !== "SuperApi" && classMetadata.annotatedClass !== methodMetadata.className){
			continue
		}

		// 函数的 metadata
		const {requestMethod, path, headers} = methodMetadata.metadataValue as {
			path: string,
			headers?: Array<[string, string]>,
			requestMethod: RequestMethod
		}

		// 函数入参的 metadata
		// todo 支持多参数传入, 当前默认将首个参数作为请求参数
		const paramMetadataList = paramMetadataManager?.findAll(undefined, methodMetadata.annotatedMethod) ?? [];

		instance[methodMetadata.annotatedMethod] = new Proxy(
			instance[methodMetadata.annotatedMethod]
			, {
				  apply(target: any, thisArg: any, argArray: any[]): any {
					let params = argArray?.[0]
					if (requestMethod == RequestMethod.GET){
						if (typeof params == "undefined"){
							return new Promise(()=>{
								axiosRequest.get(`${basePath}${path}`)
							})
						}else if (typeof params != "object"){
							return new Promise(()=>{
								axiosRequest.get(`${basePath}${path}/${params}`)
							})
						}else {
							return new Promise((resolve,reject) => {
								axiosRequest.get(
									`${basePath}${path}?${ObjectUtil.toGetParams(params)}`
							)
						})}
					}else {
						return new Promise(()=>{
							return axiosRequest.post(`${basePath}${path}`,params)
						})
					}
				}
			}
		)
	}

	return instance as CLASS
}

const metadataDomain = "Request";

export enum RequestMethod {
	GET = "GET",
	POST = "POST"
}
export function HttpMapping(basePath: string, clientName?: string){
	return Decoration.classAnnotation(metadataDomain,HttpMapping.name,{basePath,clientName})
}

export function PostMapping(path: string, headers?: Array<[string, string]>){
	return Decoration.methodAnnotation(
		metadataDomain,
		RequestMethod.POST,
		{requestMethod: RequestMethod.POST, path, headers}
	)
}



export function GetMapping(
	path: string,
	headers?: Array<[string, string]>
) {
	return Decoration.methodAnnotation(
		metadataDomain,
		RequestMethod.GET,
		{requestMethod: RequestMethod.GET, path, headers}
	)
}

export function RequestParam(){
	return Decoration.parameterAnnotation(
		metadataDomain,
		RequestParam.name
	)
}

export function RequestBody() {
	return Decoration.parameterAnnotation(
		metadataDomain,
		RequestBody.name
	)
}
