declare function local:json-array($node){
  let $array-name := if(fn:name($node) eq "OR") then "should" 
                     else if(fn:name($node) eq "AND") then "must" 
                     else if(fn:name($node) eq "zone") then "must" 
                     else if(fn:name($node) eq "NOT") then "must_not"
                     else ()
  let $array := for $each-node in $node/element()
                                return local:xml-to-json($each-node)
  let $array-string := fn:concat('"',$array-name,'":[',fn:string-join($array,","),']')
    return fn:concat('{"',"bool",'":{',$array-string,'}}')
};

declare function local:json-object($node){
  let $json-object-name := if(fn:name($node) eq "field") then "fid" else "tval"
  let $json-object-value := if(fn:name($node) eq "field") then fn:data($node/@name) else xs:string($node/text())
  let $json-object := fn:concat('{"',$json-object-name,'":"',$json-object-value,'"}')
  return  fn:concat('{"',"match",'":',$json-object,'}')
};

declare function local:xml-to-json($node){
  typeswitch($node)
  case element(query) return fn:concat('{"',fn:name($node),'":',local:xml-to-json($node/element()),'}')
  case element(OR) return local:json-array($node)
  case element(AND) return local:json-array($node)
  case element(zone) return local:json-array($node)
  case element(NOT) return local:json-array($node)                           
  case element(field) return local:json-object($node)
  case element(term) return local:json-object($node)                         
  default return ()                         
};

let $xml := <query version="1.0">
  <zone>
    <field name="TF" />
    <AND>
      <term stem="1">television</term>
      <NOT>
        <OR>
          <term stem="1">remote</term>
          <term stem="1">color</term>
        </OR>
      </NOT>
    </AND>
  </zone>
</query>
let $json := local:xml-to-json($xml)

return $json