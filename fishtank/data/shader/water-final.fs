//The height map of the water surface and the texure with the rendered scene
uniform sampler2D heightMap, sceneMap;
//uniform sampler2D velMap;
//The distance of the horizontal and vertical neighbors in texture coordinates.
uniform float dx, dy;
//Determines if the heightMap has to be displayed (debug)
uniform bool showHeightMap;

varying vec2 texCoord;

//Values [-1.0, 1.0] are stored in a color texture.
//If the value > 0 it is stored in the red channel.
//If the value is < 0 it is stored in the greed channel.
//The other two channels are unused.
//However the alpha channel is set to 1.0 for displaying.
//The following two methods convert values from/to a smapler2D
//while dealing wiht interpolation issues.

//Converts color to a [-1.0, 1.0] value.
float fromColor(vec4 value) 
{
	if(value.r > value.g) return value.r;
	else return -value.g;
}

//Converts a [-1.0, 1.0] value to a color.
vec4 toColor(float value)  
{
	if(value > 0.0) return vec4(value,0.0,0.0,1.0);
	else return vec4(0.0,-value,0.0,1.0);
}

//Uses a height map to distort a texture.
//Has to render to the fb or a fbo that is used for a subsequent effect.
void main (void)
{	
	//Epsilon for == 0 test
	float epsilon = 0.1;

	//Get the height of the water surface
	float h = fromColor(texture2D(heightMap, texCoord));

	//Apply epsilon to prevent noise
	if((h < epsilon) && (h > -epsilon)) h = 0.0;

	//Do distortion
	if(! showHeightMap) {
		
		//Calculate coordinates of von Neumann neighbors
		vec2 u = texCoord + vec2(0.0,-dy);
		vec2 l = texCoord + vec2(-dx,0.0);
		vec2 r = texCoord + vec2(dx,0.0);
		vec2 lower = texCoord + vec2(0.0,dy);

		//Get their heigths
		float valU = fromColor(texture2D(heightMap, u));
		float valD = fromColor(texture2D(heightMap, lower));
		float valL = fromColor(texture2D(heightMap, l));
		float valR = fromColor(texture2D(heightMap, r));

		//Calculate pseudo normal upon the neighbor's values
		vec2 pseudoNormal = vec2( valL - valR, valU - valD );
		
		//calculate weight for blending with a blue color
		float h_abs = abs(h) * 0.25;

		float distortion_strength = 0.02;
		
		//Apply distortion and blending
		gl_FragColor = ((1.0-h_abs) * texture2D(sceneMap, texCoord + pseudoNormal * distortion_strength)) + (h_abs) * vec4(0.25,0.37,0.57,1.0);

	    //Blend with original scene
	    float orig_scn_blend_str = 0.3;
	    gl_FragColor = 	(1.0-orig_scn_blend_str) * gl_FragColor + orig_scn_blend_str * texture2D(sceneMap, texCoord);


		gl_FragColor.w = 1.0;
		//gl_FragColor = gl_FragColor + texture2D(heightMap, texCoord);
		//gl_FragColor = 0.5 * gl_FragColor + 0.5 * texture2D(heightMap, texCoord);
	}
	//Display heightMap (debug)
	else {
		gl_FragColor = texture2D(heightMap, texCoord);
	}
}
