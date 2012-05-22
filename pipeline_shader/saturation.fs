uniform sampler2D jvr_Texture0;
uniform float saturation;

varying vec2 texCoord;

void main (void) {

  vec4 tex = texture2D( jvr_Texture0, texCoord );
  float c = (tex.r + tex.g + tex.b) / 3.0;
  vec4 bw = vec4( c, c, c, tex.a );
  gl_FragColor = mix( bw, tex, saturation );
}
