// Run this script to export a properly-formatted plugins.yaml and plugin-catalog.yaml from an existing controller.

def plugins  = Jenkins.instanceOrNull.pluginManager.plugins.collectEntries { [it.shortName, it.version] }.sort { it.key }
def names    = plugins.collect { "- id: ${it.key}" }
def versions = plugins.collect { "    ${it.key}:\n      version: ${it.value}" }

return """
---- plugins.yaml
plugins:
${names.join('\n')}

---- plugin-catalog.yaml
type: plugin-catalog
version: '1'
name: cloudbees-catalog
displayName: CloudBees Plugin Catalog
configurations:
- description: cloudbees
  includePlugins:
${versions.join('\n')}
"""
